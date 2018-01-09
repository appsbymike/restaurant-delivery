package del.res.utilities;

import java.io.File;
import java.net.URLDecoder;

public class GetPath {
	public String getPath() {
		try {
			String[] path = URLDecoder.decode(this.getClass().getClassLoader().getResource("").getPath(),"UTF-8").split("/WEB-INF/classes/");
			return new File(path[0]).getPath();
		}
		catch (Exception e) {
			return null;
		}
		
	}
}
