package kr.loner.kotlinalgorithmstudy

import java.util.*

/**
 * KotlinAlgorithmStudy
 * Class: Day3
 * Created by pyg10.
 * Created On 2021-07-01.
 * Description:
 */

fun main(){

    val matrix = MatrixBorderRotation()
    matrix.solution(3,3, arrayOf(intArrayOf(1,1,2,2), intArrayOf(1,2,2,3), intArrayOf(2,1,3,2),
         intArrayOf(2,2,3,3)))

    val bracket = CorrectBracket()
    bracket.solution("[](){}")

}

/**
 *
 * x1 y1 x2 y2
 *
 * x1 y1 -> x1, y2 -> x2, y2 -> x2, y1 -> x1, y1
 * 1,1 -> 1,2 -> 2,2 -> 2,1 -> 1,1
 * 1,2 -> 1,3 -> 2,3 -> 2,2 -> 1,2
 *
 * 100 97 [1,1,100,97] -> [1]
 *
 */

class MatrixBorderRotation{

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {

        val initArray = Array(rows) {row -> IntArray(columns) { i -> row*columns + i + 1 } } // 행렬 생성

        val minResult = mutableListOf<Int>()

        queries.forEach {
            var blankValue = 0
            val result = mutableListOf<Int>()

            for(i in it[1]..it[3]){// → 방향 회전
                if (i == it[1]){
                    blankValue = initArray[it[0]-1][i-1] //처음 회전방향은 첫 값이 비어있기때문에
                    result.add(blankValue)
                    continue
                }
                else{
                    val temp = blankValue
                    blankValue = initArray[it[0]-1][i-1]
                    result.add(blankValue)
                    initArray[it[0]-1][i-1] = temp
                }
            }

            for (i in it[0]..it[2]){// ↓ 방향 회전
                if (i == it[0])// 처음에는 이미 동일값을 가지고있기때문에 스킵
                    continue
                else{
                    val temp = blankValue
                    blankValue = initArray[i-1][it[3]-1]
                    result.add(blankValue)
                    initArray[i-1][it[3]-1] = temp
                }
            }

            for (i in it[3] downTo it[1]){// ← 방향 회전
                if (i == it[3])
                    continue
                else{
                    val temp = blankValue
                    blankValue = initArray[it[2]-1][i-1]
                    result.add(blankValue)
                    initArray[it[2]-1][i-1] = temp
                }
            }

            for (i in it[2] downTo it[0]){// ↑ 방향 회전
                if (i == it[2])
                    continue
                else{
                    val temp = blankValue
                    blankValue = initArray[i-1][it[1]-1]
                    result.add(blankValue)
                    initArray[i-1][it[1]-1] = temp
                }
            }

            if (!result.isNullOrEmpty()) {
                result.sort() // 오름차순 정렬
                minResult.add(result[0]) // 최소값 넣어줌.
            }
        }

        return minResult.toIntArray()
    }

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