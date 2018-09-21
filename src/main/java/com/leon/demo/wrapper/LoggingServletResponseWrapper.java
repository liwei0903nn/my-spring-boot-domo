package com.leon.demo.wrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class LoggingServletResponseWrapper extends HttpServletResponseWrapper {

	private MyWriter myWriter;

	public LoggingServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		myWriter = new MyWriter(super.getWriter());
		return myWriter;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return super.getOutputStream();
	}

	public MyWriter getMyWriter() {
		return myWriter;
	}

	public String getMyContent() {
		return getMyWriter().getContent();
	}

}

class MyWriter extends PrintWriter {

	private StringBuilder buffer;

	public MyWriter(Writer out) {
		super(out);
		buffer = new StringBuilder();
	}

	public String getContent() {
		return buffer.toString();
	}

	@Override
	public void write(int c) {
		// TODO Auto-generated method stub
		super.write(c);
		buffer.append(c);
	}

	@Override
	public void write(char[] buf, int off, int len) {
		// TODO Auto-generated method stub
		super.write(buf, off, len);
		buffer.append(buf, off, len);
	}

	@Override
	public void write(char[] buf) {
		// TODO Auto-generated method stub
		super.write(buf);
		buffer.append(buf);
	}

	@Override
	public void write(String s, int off, int len) {
		// TODO Auto-generated method stub
		super.write(s, off, len);
		buffer.append(s, off, len);
	}

	@Override
	public void write(String s) {
		// TODO Auto-generated method stub
		super.write(s);
		buffer.append(s);
	}
}

class MyServletOutputStream extends ServletOutputStream {

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub

	}

}
