package schwimmer.ufo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class UFOSightingMain {

	public static void main(String[] args) throws IOException {

		final BufferedReader in = new BufferedReader(new FileReader("./ufo_awesome.json"));

		final Gson gson = new Gson();

		final UFOSighting array[] = gson.fromJson(in, UFOSighting[].class);

		System.out.println(array.length);

		in.close();

	}

}
