package kr.loner.kotlinalgorithmstudy.loner

import java.util.Collections.rotate




fun main(){

}
class `테두리_회전하기`{
    var matrix:Array<IntArray> = arrayOf(intArrayOf())

    fun solution(rows: Int, columns: Int, queries: Array<IntArray?>): IntArray? {

        this.matrix = Array(rows) { IntArray(columns) } // 행렬 생성
        val answer = IntArray(queries.size) // 정답 배열
        for (i in 0 until rows) {  // 행렬 초기화
            for (j in 0 until columns) {
                matrix[i][j] = i * columns + j + 1
            }
        }
        for (i in queries.indices) { // 회전하고 최솟값 answer에 저장
            answer[i] = rotate(queries[i]!!)
        }
        return answer
    }

    fun rotate(qurey: IntArray ):Int{
        var r1 = qurey[0]-1
        var c1 = qurey[1]-1
        var r2 = qurey[2] - 1
        var c2 = qurey[3] - 1
        var temp = matrix[r1][c1]
        var min = temp
        for(i in 0..r2){
            matrix[i][c1] = matrix[i+1][c1]
            if(min > matrix[i][c1]) min = matrix[i][c1]
        }
        for(i in 0..c2){
            matrix[r2][i] = matrix[r2][i+1]
            if(min > matrix[r2][i]) min = matrix[r2][i]
        }
        for (i in 0..r1){
            this.matrix[i][c2] = this.matrix[i-1][c2]
            if(min > this.matrix[i][c2]) min = this.matrix[i][c2]
        }
        for(i in 0..c1){
            matrix[r1][i] = matrix[r1][i-1]
            if(min > matrix[r1][i]) min = matrix[r1][i]
        }

        matrix[r1][c1+1] = temp
        return min
    }

}