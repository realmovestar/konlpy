from konlpy.tag import Twitter

twitter = Twitter()

"""
런타임에 트위터 사전에 접근하는 방법

twitter-korean-text-2.4.3.jar 파일 내부의 사전에 없는 단어를 추가할 수 있다.
"""

# 사전 접근
dic = twitter.jki.koreanDictionary()

# 사용가능한 사전 목록
print(twitter.jki.shortCut())
"""
Map(E -> Exclamation, e -> Eomi, s -> Suffix, n -> Number, N -> Noun, j -> Josa, J -> Adjective, A -> Adverb, a -> Alpha, v -> VerbPrefix, V -> Verb, p -> NounPrefix, C -> Conjunction, r -> PreEomi, D -> Determiner, o -> Others)
"""

# Josa 사전 접근
josa = dic.get(twitter.jki.koreanPosWithName("Josa"))
# print(josa)

# Noun 사전 접근
noun = dic.get(twitter.jki.koreanPosWithName("Noun"))
# print(noun)

# 테스트
twitter.nouns("슈퍼맨, 앤트맨에 이어서 바퀴맨이 나왔다")
# ['슈퍼맨', '앤트맨', '바퀴', '맨']

# noun 사전에 신조어(바퀴맨) 추가
noun.get().add("바퀴맨")

# 확인
twitter.nouns("슈퍼맨, 앤트맨에 이어서 바퀴맨이 나왔다")
# ['슈퍼맨', '앤트맨', '바퀴맨']

