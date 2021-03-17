This is a simple qrcode builder that is build ontop of the awesome ZXing library for barcode generation: https://github.com/zxing/zxing

QRCodeBuilder
=========

The builder is very simple to use, as the following example will shows.

Create a QRCode with dimensions 250*250, a image overlay and some data:

```java
package org.skrymer.qrbuilder;

import org.skrymer.qrbuilder.decorator.ColoredQRCode;
import org.skrymer.qrbuilder.decorator.ImageOverlay;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static final int WIDTH = 250;
    public static final int HEIGHT = 250;

    private static final String youtube = "bit.ly/3tlkhSO";

    public static void main(String[] args) {
        QRCode.ZXingBuilder.build(builder ->
                builder
                        .withSize(WIDTH, HEIGHT)
                        .and()
                        .withData(youtube)
                        .and()
                        .withDecorator(ColoredQRCode.colorizeQRCode(Color.decode("#FF0000")))
                        .and()
                        .withDecorator(ImageOverlay.addImageOverlay(readImage("src/test/resources/images/IconYouTube.png")))
                        .and()
                        .withCharSet(StandardCharsets.UTF_8)
                        .verify(true)
        ).toFile("./qrCode/youtube.png", "PNG");
    }

    public static BufferedImage readImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```
The following qrCode is then generated:

![alt text](./qrCode/youtube.png "QRCode")

## Decorators

The builder uses the decorators to decorate(obviously) the generated QRCode. 

Decorators currently available:
* ImageOverlay 
* ColoredQRCode 

You can create new Decorators by implementing the Decorator interface
