package reporting;

import org.monte.media.Format;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Custom ScreenRecorder to control file naming for scenario videos.
 */
public class SpecializedScreenRecorder extends ScreenRecorder {

    private String name;

    public SpecializedScreenRecorder(GraphicsConfiguration cfg, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, null, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, movieFolder);
        this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        }
        return new File(movieFolder, name + "_" + System.currentTimeMillis() + "." + Registry.getInstance().getExtension(fileFormat));
    }
}