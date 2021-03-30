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

    private static final String linkedin = "bit.ly/3fqAoux";
    private static final String gitHub = "bit.ly/3m5AnNY";
    private static final String youtube = "bit.ly/2QMOCeV";

    public static void main(String[] args) {
        QRCode.ZXingBuilder.build(builder ->
                builder
                        .withSize(WIDTH, HEIGHT)
                        .and()
                        .withData(linkedin)
                        .and()
                        .withDecorator(ColoredQRCode.colorizeQRCode(Color.decode("#0A66C2")))
                        .and()
                        .withDecorator(ImageOverlay.addImageOverlay(readImage("src/test/resources/images/IconLikedin.png")))
                        .and()
                        .withCharSet(StandardCharsets.UTF_8)
                        .verify(true)
        ).toFile("./qrCode/linkedin.png", "PNG");

        QRCode.ZXingBuilder.build(builder ->
                builder
                        .withSize(WIDTH, HEIGHT)
                        .and()
                        .withData(gitHub)
                        .and()
                        .withDecorator(ColoredQRCode.colorizeQRCode(Color.decode("#161B22")))
                        .and()
                        .withDecorator(ImageOverlay.addImageOverlay(readImage("src/test/resources/images/IconGitHub.png")))
                        .and()
                        .withCharSet(StandardCharsets.UTF_8)
                        .verify(false)
        ).toFile("./qrCode/gitHub.png", "PNG");

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
