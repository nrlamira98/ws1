package controller;

import java.awt.Container;

import java.io.File;
import java.lang.String;
import java.lang.System;

import javax.swing.JFileChooser;

public class FileApi extends File{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public FileApi(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}
	
	public FileApi(String parentPath, String childPath)
	{
		super(parentPath, childPath);
	}
	
	/*
	 * Cari bagaimana nak find file
	 */
	public File findFile(String pathname)
	{
		File fail = new File(pathname);
		return fail;
	}
	
	
	
	public static void main(String[] args) {
		
		FileApi fileApi = new FileApi("D:\\bengkel1\\");
		
		for(String  name : fileApi.list())
		{
			System.out.println(name);	
			
		}
		
		
		JFileChooser chooser = new JFileChooser(":\\D");
		chooser.showOpenDialog(null);
		
		
		
		
		
	
	}

}
