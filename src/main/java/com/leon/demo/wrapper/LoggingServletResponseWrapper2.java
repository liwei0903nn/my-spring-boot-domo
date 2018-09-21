package com.leon.demo.wrapper;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class LoggingServletResponseWrapper2 extends HttpServletResponseWrapper {

	private LoggingServletOutpuStream loggingServletOutpuStream = null;
	private ResponsePrintWriter myPrintWriter = null;

	private final HttpServletResponse delegate;

	public LoggingServletResponseWrapper2(HttpServletResponse response) {
		super(response);
		delegate = response;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (loggingServletOutpuStream == null) {
			loggingServletOutpuStream = new LoggingServletOutpuStream(super.getOutputStream());
		}

		return loggingServletOutpuStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
//		return new PrintWriter(loggingServletOutpuStream.baos);
		if (myPrintWriter == null) {
			if (loggingServletOutpuStream == null) {
				loggingServletOutpuStream = new LoggingServletOutpuStream(super.getOutputStream());
			}
			String responseEncoding = delegate.getCharacterEncoding();
			myPrintWriter = new ResponsePrintWriter(loggingServletOutpuStream,
					responseEncoding != null ? responseEncoding : UTF_8.name());
		}

		return myPrintWriter;
	}

	public Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<>(0);
		for (String headerName : getHeaderNames()) {
			headers.put(headerName, getHeader(headerName));
		}
		return headers;
	}

	public String getContent() {
		try {
			String responseEncoding = delegate.getCharacterEncoding();
			return loggingServletOutpuStream.baos.toString(responseEncoding != null ? responseEncoding : UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			return "[UNSUPPORTED ENCODING]";
		}
	}

	public byte[] getContentAsBytes() {
		return loggingServletOutpuStream.baos.toByteArray();
	}

	private class LoggingServletOutpuStream extends ServletOutputStream {

		private ServletOutputStream delegate = null;
		private ByteArrayOutputStream baos = new ByteArrayOutputStream();

		public LoggingServletOutpuStream(ServletOutputStream delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public boolean isReady() {
			return delegate.isReady();
		}

		@Override
		public void setWriteListener(WriteListener writeListener) {
		}

		@Override
		public void write(int b) throws IOException {
			delegate.write(b);
			baos.write(b);
		}

		@Override
		public void write(byte[] b) throws IOException {
			delegate.write(b);
			baos.write(b);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			delegate.write(b, off, len);
			baos.write(b, off, len);
		}
	}

	private class ResponsePrintWriter extends PrintWriter {
		private ResponsePrintWriter(OutputStream buf, String characterEncoding) throws UnsupportedEncodingException {
			super(new OutputStreamWriter(buf, characterEncoding));
		}

		@Override
		public void write(char buf[], int off, int len) {
			super.write(buf, off, len);
			super.flush();
		}

		@Override
		public void write(String s, int off, int len) {
			super.write(s, off, len);
			super.flush();
		}

		@Override
		public void write(int c) {
			super.write(c);
			super.flush();
		}
	}
}
