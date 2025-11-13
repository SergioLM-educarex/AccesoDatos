package tema1.ficheros4flujosostreams.ejercicios.stream3almacenaObjetosenBinario.ej27;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {

	public MyObjectOutputStream(OutputStream out) throws IOException, SecurityException {
		super(out);
		
	}
	
	protected MyObjectOutputStream() throws IOException, SecurityException {
		
		super();
		
		}
	
		@Override
		protected void writeStreamHeader() throws IOException {
			   reset();
		}

}
