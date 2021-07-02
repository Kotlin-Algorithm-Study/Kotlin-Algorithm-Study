package kr.loner.kotlinalgorithmstudy

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