 import java.io.File;

class FileDemo
 {

	static void p(String s)
	 {
		System.out.println(s);
	 }

	public static void main(String args[]) 
	{
		File fl = new File("/java/COPYRIGHT");
		p(" File Name: " +f1.getName());
		p(" Path: "+f1.getPath()); 
		p("Abs Path: " +f1.getAbsolutePath());
		p("Parent:" +f1.getParent());
		p(fl.exists() ? "exists" : "does not exist");
		p(fl.canWrite() ? "is writeable" : "is not writeable");
		p(f1.canRead() ? "is readable" : "is not readable");
		p("is "+ (f1.isDirectory() ?" " : "not" + "a directory"));
		p(fl.isFile() ? "is normal file": "might be a named pipe");
		p(fl.isAbsolute() ? "is absolute" : "is not absolute");
		p("File last modified: " +f1.lastModified());
		p("File size: " +f1.length() + " Bytes");
	
	}
}