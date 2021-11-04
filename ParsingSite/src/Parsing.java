import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parsing {
	private static Logger log = Logger.getLogger(Parsing.class.getName());
	public Document doc;

	public void GetWeb(String url) throws IOException {
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
		}
	}

	public String[] ParsingWeb() throws SecurityException, IOException {
		try {
			String str = doc.text();
			String[] masstr = str
					.split("[' ' ',' '|' '©' '@' '.' '!' '»' '??' '?' ';' '\"' ':' '[' ']' '(' ')' '\n' '\r' '\t']+");
			return masstr;
		} catch (Exception e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
			return null;
		}

	}
}
