package kr.loner.kotlinalgorithmstudy.ryan

import java.util.*

/**
 * KotlinAlgorithmStudy
 * Class: Day4
 * Created by pyg10.
 * Created On 2021-07-05.
 * Description:
 */

fun main(){

}


class CorrectBracket{
    fun solution(s: String): Int {
        var answer: Int = 0

        for ( i in s.indices){ // 회전 몇개까지 시킬껀가
            val value = s.toMutableList() // 리스트로 변환
            for (j in 0 until i){ // 회전시킬 갯수
                value.add(value[0]) // 처음것을 맨마지막에 삽입
                value.removeAt(0) // 처음을 삽입해줬으니 제거
            }

            if (isCorrect(value)) // 옳은 괄호라면 카운트
                answer++
        }
        return answer
    }

    private fun isCorrect(bracket: MutableList<Char>): Boolean{
        val stack = Stack<Char>()

        for (i in bracket.indices){
            if (stack.isEmpty()) // 스택이 비어있다면 삽입
                stack.push(bracket[i])
            else{
                when(bracket[i]){ // 비어있지않다면 } ] ) 의 경우 스택의내용과 비교한후 삽입하거나 꺼냄
                    '}' -> if (stack.peek() == '{') stack.pop() else stack.push(bracket[i])
                    ']' -> if (stack.peek() == '[') stack.pop() else stack.push(bracket[i])
                    ')' -> if (stack.peek() == '(') stack.pop() else stack.push(bracket[i])
                    else -> stack.push(bracket[i]) // } ] ) 외의경우는 삽입
                }

            }
        }
        return stack.isEmpty() // 비어있다면 옳은괄호 비어있지않다면 옳지않은 괄호
    }
}

class CandidateKey{

    fun solution(relation: Array<Array<String>>): Int {
        var answer = 0

        val setKey = mutableListOf<Key>()
        relation.forEach { setKey.add(Key(it[0], it[1], it[2], it[3])) }

        if (setKey.map { it.studentID }.toSet().size == setKey.size) answer++


        return answer
    }

    data class Key(val studentID: String, val name: String, val major: String, val grade: String)

}