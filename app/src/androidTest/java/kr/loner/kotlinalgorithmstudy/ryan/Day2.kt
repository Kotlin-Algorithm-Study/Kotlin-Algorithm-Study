package kr.loner.kotlinalgorithmstudy.ryan

import java.util.*
import kotlin.math.ceil

/**
 * KotlinAlgorithmStudy
 * Class: Day2
 * Created by pyg10.
 * Created On 2021-06-29.
 * Description:
 */

fun main() {

    val changeBracket = ChangeBracket()
    changeBracket.solution("()))((()")

//    val max = MaxCalculator()
//    println("result -> ${max.solution("200-300-500-600*40+500+500")}")

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

/**
 *
 * TestCase
 *
 * (()())() -> (()())()
 * )( -> ()
 * ()))((() -> ()(())()
 *
 */

class ChangeBracket{
    fun solution(p: String): String {

        //1. 입력이 빈문자열인경우 빈문자열 반환
        if (p.isEmpty()) return p

        //2. 문자열 w를 균형잡힌 두 문자열 u, v로 변환
        val isCorrectIndex = checkCorrect(p)
        val u = p.slice(0 until isCorrectIndex)
        val v = p.slice(isCorrectIndex until p.length)

        var answer = ""

        //3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
        if (isCorrect(u))  return u + solution(v) // 3.1 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.

        else{ //4. 올바른 문자열이 아닌경우
            answer += "(" // 4.1 빈문자열에 첫번째문자로 (

            answer += solution(v) // 4.2 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.

            answer += ")" // 4.3 ')'를 다시 붙입니다.

            //4.4 u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            u.forEachIndexed{index, c ->
                if (index != 0 && index != u.length-1){
                    answer += if (c == '(') ")"
                    else "("
                }
            }
        }
        
        //4.5 생성된 문자열을 반환합니다.
        return answer
    }

    private fun checkCorrect(p: String) : Int{

        var pos = 0 // ( ) 갯수가 같은위치의 카운트
        var left = 0 // ( 카운트
        var right = 0 // ) 카운트

        run {
            p.forEachIndexed { index, it ->
                if (it == '(') left++
                else right ++

                println("index -> $index left -> $left right -> $right")

                if (left == right) {
                    pos = index + 1 // 스트링의 인덱스는 0부터시작이기때문에 +1을 해주어야함.
                    return@run
                }
            }
        }

        return pos

    }

    private fun isCorrect(p: String): Boolean{ // 올바른괄호 문자열인지 확인

        val stack = Stack<Char>()

        p.forEach {
            if (stack.isEmpty()) // 비어있다면 push
                stack.push(it)
            else{
                if (it == ')' && stack.peek() == '(') //비어있지않다면 )경우에 스택의 마지막값이 ( 이라면 pop
                    stack.pop()
                else // 아닌경우는 push
                    stack.push(it)
            }
        }

        return stack.isEmpty()

    }
}



/**
 *
 * Test Case
 * expression = 100-200*300-500+20 -> 60420
 * expression = 50*6-3*2 -> 300
 * expression = 200-300-500-600*40+500+500 -> 1248000
 *
 */

class MaxCalculator {
    fun solution(expression: String): Long {
        var answer: Long = 0

        val priorityOperator = arrayOf("*+-", "*-+", "+*-", "+-*", "-*+", "-+*") // 우선순위

        val builder = StringBuilder() // 자르기전 문자열을 나타내기위함.

        val calculator = Stack<String>() // 하나씩 넣으면서 계산을 할 생각이기때문에 스택을 이용 바로앞의 내용을 pop으로 꺼내올수있기때문

        val result = mutableListOf<Long>() // 계산결과를 담을 리스트

        expression.forEach { //문자열 자르기좋게 * + - 앞뒤에 공백추가
            when (it) {
                '*' -> builder.append(" * ")
                '-' -> builder.append(" - ")
                '+' -> builder.append(" + ")
                else -> builder.append(it.toString())
            }
        }


        priorityOperator.forEach { operator ->
            val beforeExpression = builder.toString().split(" ").toMutableList() // 공백을 기준으로 문자열을 자름. 수정이 필요하기때문에 mutableList로 변환
            var isCalculator = false // 직전에 계산을 하였는지 설정 자바의경우 for문 안의 변수를 수정할수있지만(ex) for(int i =0; i<5; i++) i = i+1) 코틀린의 경우에는 불가능하기때문에 이를 핸들링하기위한 변수
            operator.forEach { singleOperator ->

                for (i in beforeExpression.indices){

                    if (isCalculator) { //계산을 한적이 있다면
                        isCalculator = !isCalculator
                        continue // 이번꺼는 넘기고 다음부터
                    }else{
                        if (beforeExpression[i] == singleOperator.toString()){ // 연산자가나왔다면
                            val beforeValue = calculator.pop() //이전값을 꺼낸후
                            when(singleOperator){
                                '*' ->{
                                    calculator.push((beforeValue.toLong() * beforeExpression[i + 1].toLong()).toString()) // 계산하여 다시 삽입
                                    isCalculator = !isCalculator // 계산을 하였으니 반대값으로 설정
                                }
                                '-' ->{
                                    calculator.push((beforeValue.toLong() - beforeExpression[i + 1].toLong()).toString())
                                    isCalculator = !isCalculator
                                }
                                '+' ->{
                                    calculator.push((beforeValue.toLong() + beforeExpression[i + 1].toLong()).toString())
                                    isCalculator = !isCalculator
                                }
                            }
                        }else calculator.push(beforeExpression[i]) // 연산자와 동일하지않다면 스택에 삽입
                    }
                    println("all -> $operator single -> $singleOperator $calculator")
                }

                beforeExpression.clear() // 1회 반복할때마다 계산한 결과값들이 들어가야하기때문에 먼저 리스트 초기화
                val calculatorSize = calculator.size // 스택의 사이즈가 변경되기때문에 밖에서 변수로 설정
                for (i in 0 until calculatorSize){
                    beforeExpression.add(calculator.pop()) // 스택의 내용을 넣음
                }
                beforeExpression.reverse() // 스택은 선입후출이기때문에 뒤의내용이 먼저나오게됨. 따라서 뒤집어주어야 정상적

                println(beforeExpression)
            }
            result.add(beforeExpression[0].removePrefix("-").toLong()) // 마지막에는 한개만 가지고있는 리스트가 반환되기에 -인경우 삭제해주고 결과리스트에 삽입
        }
        result.sortDescending() // 내림차순으로 변경
        return result[0] // 내림차순이기에 처음값이 가장 큼.
    }
}