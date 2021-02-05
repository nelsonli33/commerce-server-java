package com.shopflix.merchant.service.product;

import java.io.ByteArrayOutputStream;

public class ProductImage
{
    private String filename;
    private int width;
    private int height;
    private ByteArrayOutputStream outputStream;

    public ProductImage(String filename, int width, int height, ByteArrayOutputStream outputStream)
    {
        this.filename = filename;
        this.width = width;
        this.height = height;
        this.outputStream = outputStream;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public ByteArrayOutputStream getOutputStream()
    {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream)
    {
        this.outputStream = outputStream;
    }
}
