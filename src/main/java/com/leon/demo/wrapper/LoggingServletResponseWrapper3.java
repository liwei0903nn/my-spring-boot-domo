package com.leon.demo.wrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class LoggingServletResponseWrapper3 extends HttpServletResponseWrapper {

	private StringWriter sw = new StringWriter(1024 * 1024);

	public LoggingServletResponseWrapper3(HttpServletResponse response) {
		super(response);
	}

	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(sw);
	}

	public ServletOutputStream getOutputStream() throws IOException {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return sw.toString();
	}

}
