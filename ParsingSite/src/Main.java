import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws IOException, SecurityException {
		Scanner sc = new Scanner(System.in);
		System.out.print("¬ведите url сайта: ");
		String url = sc.next();
		WorkWithBD bd = new WorkWithBD();
		bd.ConnectToBD();
		Parsing pars = new Parsing();
		pars.GetWeb(url);
		String masstr[];
		try {
			masstr = pars.ParsingWeb();
		} catch (Exception e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
			masstr = null;
		}
		for (String check : masstr) {
			if (bd.FindWord(check)) {
				bd.Increment(check);
			} else
				bd.InsertIntoTable(check);
		}
		bd.PrintBD();
	}

}
