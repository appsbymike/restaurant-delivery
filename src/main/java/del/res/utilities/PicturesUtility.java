package del.res.utilities;

import java.io.File;
import java.util.ArrayList;

import del.res.models.Picture;

public class PicturesUtility {
	
	
	public ArrayList<Picture> getItemPictures(){
		ArrayList<Picture> pics = new ArrayList<Picture>();
		System.out.println("Before file");
		GetPath getPath = new GetPath();
		String path = getPath.getPath();
		File folder = new File(path + "\\Images\\");
		System.out.println("After File");
		System.out.println(folder.getPath() + " " + folder.exists());
		for (File image : folder.listFiles()) {
			String name = image.getName();
			System.out.println(name);
			String regex = "item.*.jpg";
			if (name.matches(regex)) {
				Picture pic = new Picture();
				pic.setUrl("Images/" + name);
				pics.add(pic);
			}
		}
		
		return pics;
	}
	
	public ArrayList<Picture> getStorePictures(){
		ArrayList<Picture> pics = new ArrayList<Picture>();
		GetPath getPath = new GetPath();
		String path = getPath.getPath();
		File folder = new File(path + "\\Images\\");
		for (File image : folder.listFiles()) {
			String name = image.getName();
			String regex = "location.*.jpg";
			if (name.matches(regex)) {
				Picture pic = new Picture();
				pic.setUrl("Images/" + name);
				pics.add(pic);
			}
		}
		return pics;
	}
}
