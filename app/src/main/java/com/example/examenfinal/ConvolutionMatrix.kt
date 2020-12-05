package com.example.examenfinal

import android.graphics.Bitmap
import android.graphics.Color


public class ConvolutionMatrix{
    val SIZE = 3

    var Matrix: Array<DoubleArray>
    var Factor = 1.0
    var Offset = 1.0

    constructor(size: Int){
        Matrix = Array(size) { DoubleArray(size) }
    }

    fun setAll(value: Double) {
        for (x in 0 until SIZE) {
            for (y in 0 until SIZE) {
                Matrix[x][y] = value
            }
        }
    }

    fun applyConfig(config: Array<DoubleArray>) {
        for (x in 0 until SIZE) {
            for (y in 0 until SIZE) {
                Matrix[x][y] = config[x][y]
            }
        }
    }

    fun computeConvolution3x3(src: Bitmap?, matrix: ConvolutionMatrix): Bitmap? {
        val width = src!!.width
        val height = src.height
        val result = Bitmap.createBitmap(width, height, src.config)
        var a: Int
        var r: Int
        var g: Int
        var b: Int
        var sumR: Int
        var sumG: Int
        var sumB: Int
        val pixels = Array(SIZE) { IntArray(SIZE) }
        for (y in 0 until height - 2) {
            for (x in 0 until width - 2) {

                // get pixel matrix
                for (i in 0 until SIZE) {
                    for (j in 0 until SIZE) {
                        pixels[i][j] = src.getPixel(x + i, y + j)
                    }
                }

                // get alpha of center pixel
                a = Color.alpha(pixels[1][1])

                // init color sum
                sumB = 0
                sumG = sumB
                sumR = sumG

                // get sum of RGB on matrix
                for (i in 0 until SIZE) {
                    for (j in 0 until SIZE) {
                        sumR += (Color.red(pixels[i][j]) * matrix.Matrix[i][j]).toInt()
                        sumG += (Color.green(pixels[i][j]) * matrix.Matrix[i][j]).toInt()
                        sumB += (Color.blue(pixels[i][j]) * matrix.Matrix[i][j]).toInt()
                    }
                }

                // get final Red
                r = (sumR / matrix.Factor + matrix.Offset).toInt()
                if (r < 0) {
                    r = 0
                } else if (r > 255) {
                    r = 255
                }

                // get final Green
                g = (sumG / matrix.Factor + matrix.Offset).toInt()
                if (g < 0) {
                    g = 0
                } else if (g > 255) {
                    g = 255
                }

                // get final Blue
                b = (sumB / matrix.Factor + matrix.Offset).toInt()
                if (b < 0) {
                    b = 0
                } else if (b > 255) {
                    b = 255
                }

                // apply new pixel
                result.setPixel(x + 1, y + 1, Color.rgb(r, g, b))
            }
        }
        src.recycle()
        //src = null

        // final image
        return result
    }
}