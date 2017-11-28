package kr.lucypark.tkt;

import java.util.ArrayList;
import java.util.List;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import com.twitter.penguin.korean.util.CharArraySet;
import com.twitter.penguin.korean.util.KoreanDictionaryProvider;
import com.twitter.penguin.korean.util.KoreanPos;

import scala.Enumeration.Value;
import scala.collection.mutable.Map;


public class TktInterface {

    TwitterKoreanProcessorJava processor = null;

    public List<String> tokenize(String string, Boolean norm, Boolean stem) {

        if (norm && stem) {
            processor = new TwitterKoreanProcessorJava.Builder().build();
        } else if (norm && ! stem) {
            processor = new TwitterKoreanProcessorJava.Builder().disableStemmer().build();
        } else if (! norm && stem) {
            processor = new TwitterKoreanProcessorJava.Builder().disableNormalizer().build();
        } else if (! norm && ! stem) {
            processor = new TwitterKoreanProcessorJava.Builder()
                        .disableStemmer().disableNormalizer()
                        .build();
        }

        List<KoreanTokenizer.KoreanToken> tokens = processor.tokenize(string);

        List<String> list = new ArrayList<>();
        for (KoreanTokenizer.KoreanToken token: tokens) {
        	String str = token.text() + '/' + token.pos();
        	list.add(str);
        }
        return list;
    }

    public List<CharSequence> phrases(String string) {
        processor = new TwitterKoreanProcessorJava.Builder()
            .disableNormalizer()
            .disableStemmer()
            .enablePhraseExtractorSpamFilter()
            .build();
        return processor.extractPhrases(string);
    }

    public Map<Value, CharArraySet> koreanDictionary() {
        return KoreanDictionaryProvider.koreanDictionary();
    }

    public scala.collection.immutable.Map<Object, Value> shortCut() {
        return KoreanPos.shortCut();
    }

    public Value koreanPosWithName(String pos) {
    	return KoreanPos.withName(pos);
    }

    public static void main(String[] args) throws Exception {
        TktInterface ti = new TktInterface();

        List<String> tokens = ti.tokenize("아버지가 방에 들어가신다.", false, false);
        for (String token : tokens) {
            System.out.println(token);
        }
        System.out.println();

        List<CharSequence> phrases = ti.phrases("아버지가 방에 들어가신다.");
        System.out.println(phrases);
    }
}
