package com.example.examenfinal

import android.R.attr
import android.graphics.Bitmap
import android.graphics.Color


class BitmapProcessing {

    public fun Negativo(src: Bitmap): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                // get color on each channel
                R = 255 - Color.red(pixel)
                G = 255- Color.green(pixel)
                B = 255- Color.blue(pixel)

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    public fun Brillo(src: Bitmap, num: Int): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)

                R += num;
                if(R > 255) { R = 255; }
                else if(R < 0) { R = 0; }

                G += num;
                if(G > 255) { G = 255; }
                else if(G < 0) { G = 0; }

                B += num;
                if(B > 255) { B = 255; }
                else if(B < 0) { B = 0; }

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    public  fun Filtro(src: Bitmap, num: Int): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)

                if(num == 1){
                    R= 255
                    G = 0
                    B = 0
                    bmOut.setPixel(x, y, Color.rgb(R, G, B))
                }else if (num == 2){
                    R = -255
                    G= G
                    B = -255
                    bmOut.setPixel(x, y, Color.rgb(R, G, B))
                }else{
                    R = -255
                    G = -255
                    B= B
                    bmOut.setPixel(x, y, Color.rgb(R, G, B))
                }
            }
        }
        src.recycle()
        return bmOut
    }

    public fun Gamma(src: Bitmap, num: Double): Bitmap {

        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        val MAX_SIZE = 256;
        val MAX_VALUE_DBL = 255.0;
        val  MAX_VALUE_INT = 255;
        val REVERSE = 1.0;

        val gammaR = IntArray(MAX_SIZE)
        val gammaG = IntArray(MAX_SIZE)
        val gammaB = IntArray(MAX_SIZE)

        for (i in 0 until MAX_SIZE) {
            gammaR[i] = Math.min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / num) + 0.5).toInt()
            )
            gammaG[i] = Math.min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / num) + 0.5).toInt()
            )
            gammaB[i] = Math.min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / num) + 0.5).toInt()
            )
        }

        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                R = gammaR[Color.red(pixel)];
                G = gammaG[Color.green(pixel)];
                B = gammaB[Color.blue(pixel)];

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    public fun Contraste(src: Bitmap, num: Int): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        var R: Int
        var G: Int
        var B: Int
        var pixel: Int
        val contrast = Math.pow((100.0 + num) / 100, 2.0)

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                R = Color.red(pixel);
                R = (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0).toInt()
                if(R < 0) { R = 0; }
                else if(R > 255) { R = 255; }

                G = Color.green(pixel);
                G = (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0).toInt()
                if(G < 0) { G = 0; }
                else if(G > 255) { G = 255; }

                B = Color.blue(pixel);
                B = (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0).toInt()
                if(B < 0) { B = 0; }
                else if(B > 255) { B = 255; }

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    public fun Grises(src: Bitmap): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        var GS_RED = 0.299;
        var GS_GREEN = 0.587;
        var GS_BLUE = 0.114;
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                // get color on each channel
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)

                B = (GS_RED * R + GS_GREEN * G + GS_BLUE * B).toInt();
                G= B
                R= G
                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    public fun gaussianBlur(original: Bitmap) : Bitmap? {
        val edgeDetecionConfig = arrayOf(
            doubleArrayOf(1.0, 1.0, 1.0),
            doubleArrayOf(0.0, 0.0, 0.0),
            doubleArrayOf(-1.0, -1.0, -1.0)
        )
        val convMatrix = ConvolutionMatrix(3)
        convMatrix.applyConfig(edgeDetecionConfig)
        convMatrix.Factor = 1.0
        convMatrix.Offset = 127.0

        var final = ConvolutionMatrix(3)
        return final.computeConvolution3x3(original, convMatrix)
    }

    public fun Sepia(src: Bitmap): Bitmap{
        val width: Int = src.getWidth()
        val height: Int = src.getHeight()
        // create output bitmap
        // create output bitmap
        val bmOut = Bitmap.createBitmap(width, height, src.getConfig())
        // constant grayscale
        // constant grayscale
        val GS_RED = 0.3
        val GS_GREEN = 0.59
        val GS_BLUE = 0.11
        // color information
        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels

        // scan through all pixels
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                // get color on each channel
                A = Color.alpha(pixel)
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)
                // apply grayscale sample
                R = (GS_RED * R + GS_GREEN * G + GS_BLUE * B).toInt()
                G = R
                B = G

                // apply intensity level for sepid-toning on each channel
                R += 110
                if (R > 255) {
                    R = 255
                }
                G += 65
                if (G > 255) {
                    G = 255
                }
                B += 20
                if (B > 255) {
                    B = 255
                }

                // set new pixel color to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }
        src.recycle()
        //src = null
        return bmOut
    }
}