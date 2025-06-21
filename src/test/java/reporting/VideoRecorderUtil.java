package reporting;

import org.monte.screenrecorder.ScreenRecorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for managing screen video recording during test execution.
 */
public class VideoRecorderUtil {
    private static ScreenRecorder screenRecorder;

    /**
     * Starts the video recording and saves it to the target/videos directory.
     *
     * @param scenarioName The name of the scenario for naming the video file.
     * @throws IOException If the recorder cannot be started.
     * @throws AWTException If the screen device cannot be accessed.
     */
    public static void startRecording(String scenarioName) throws IOException, AWTException {
        File videoDir = new File("target/videos");
        if (!videoDir.exists()) videoDir.mkdirs();

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new SpecializedScreenRecorder(gc, videoDir, scenarioName.replaceAll("[^a-zA-Z0-9]", "_"));
        screenRecorder.start();
    }

    /**
     * Stops the video recording and returns the path to the saved video.
     *
     * @return The relative path to the recorded video file.
     * @throws IOException If the recorder cannot be stopped.
     */
    public static String stopRecording() throws IOException {
        if (screenRecorder != null) {
            screenRecorder.stop();
            File lastCreatedFile = screenRecorder.getCreatedMovieFiles().getFirst();
            // Return relative path for embedding in the report
            return "videos/" + lastCreatedFile.getName();
        }
        return null;
    }
}