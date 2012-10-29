package geomati.co.process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * Thread that runs an external process redirecting the output.
 */
public class ProcessThread extends Thread {
	private static final Logger logger = Logger.getLogger(ProcessThread.class);

	private String[] cmd;
	private int returnValue = 0;
	private File runPath;
	private OutputStream out;
	private OutputStream err;

	/**
	 * Creates a new thread for the external process.
	 * 
	 * @param runPath
	 *            The working directory of the subprocess.
	 * @param cmd
	 *            An array containing the command to call and its arguments.
	 * @param out
	 *            The stream where the process output must be redirected.
	 * @param err
	 *            The stream where the process error must be redirected.
	 */
	public ProcessThread(File runPath, String[] cmd, OutputStream out,
			OutputStream err) {
		this.cmd = cmd;
		this.runPath = runPath;
		this.out = out;
		this.err = err;
	}

	@Override
	public void run() {
		try {
			Process process = Runtime.getRuntime().exec(cmd, null, runPath);

			StreamPipe input = new StreamPipe(process.getInputStream(), out);
			StreamPipe error = new StreamPipe(process.getErrorStream(), err);

			input.start();
			error.start();
			returnValue = process.waitFor();
			input.join();
			error.join();
		} catch (Exception e) {
			logger.error("An error has occured while executing "
					+ "the process ", e);
			returnValue = 1;
		}
	}

	/**
	 * Returns the return value of the process.
	 * 
	 * @return The return value of the process. If any (Java) exception occurs
	 *         while executing the process, it returns 1. If this method is
	 *         called before the process finishes, the returning value is
	 *         meaningless.
	 */
	public int getReturnValue() {
		return returnValue;
	}

	/**
	 * Thread that pipes two streams.
	 */
	private class StreamPipe extends Thread {
		private InputStream input;
		private OutputStream output;

		/**
		 * Creates a new thread.
		 * 
		 * @param input
		 *            The source stream.
		 * @param output
		 *            The destination stream.
		 */
		public StreamPipe(InputStream input, OutputStream output) {
			this.input = input;
			this.output = output;
		}

		@Override
		public void run() {
			try {
				IOUtils.copy(input, output);
			} catch (IOException e) {
				logger.error("An error has occured while copying "
						+ "the process output", e);
				returnValue = 1;
			}
		}
	}
}