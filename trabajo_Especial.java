
public class trabajo_Especial {
	
	public static final int patenteMax=999;
	public static final int lugaresMax = 20;
	public static final int M = 10;
	public static final int M1 = 10;
	public static final int M2 = 100;
	public static final int M3 = 1000;
	
	public static void main(String[] args) {
		int lugares[] = new int[lugaresMax];
		int autos = 5;
		float epsilon = 0.01f;
		System.out.println("--------------------A--------------------");
		int entraron = estacionarEn(lugares, autos);
		if (entraron == -1) {
			System.out.println("Entraron todos los autos: " + entraron);						
		} else {
			System.out.println("Cantidad de autos que ingresaron hasta que hubo una colisión: " + entraron);			
		}
		System.out.println("--------------------B--------------------");
		float probabilidad = calcularProbabilidadDeColision(lugares, autos, M, "B");
		System.out.println("Probabilidad de Colision: " + probabilidad + "%");
		System.out.println("--------------------C--------------------");
		System.out.println("Autos|Probabilidad|M");
		calcularProbabilidadDeColision(lugares, 5, M1, "C");
		calcularProbabilidadDeColision(lugares, 5, M2, "C");
		calcularProbabilidadDeColision(lugares, 5, M3, "C");			
		calcularProbabilidadDeColision(lugares, 10, M1, "C");			
		calcularProbabilidadDeColision(lugares, 10, M2, "C");			
		calcularProbabilidadDeColision(lugares, 10, M3, "C");			
		calcularProbabilidadDeColision(lugares, 15, M1, "C");
		calcularProbabilidadDeColision(lugares, 15, M2, "C");
		calcularProbabilidadDeColision(lugares, 15, M3, "C");
		System.out.println("--------------------D--------------------");
		CalcularDiferenciaEpsilon(lugares, autos, epsilon);

	}

	private static void CalcularDiferenciaEpsilon(int[] lugares, int autos, float epsilon) {
		int i=1;
		int aciertos = 0;
		float probabilidad1 = 0;
		float probabilidad2 = 0;
		float resultado = 0.01f;
		while (i <= M || resultado > epsilon) {
			int entraron = estacionarEn(lugares, autos);
			if (entraron == -1) {
				aciertos++;
			}
			if (i==1) {
				probabilidad1=((float)aciertos/(float)i);
//				System.out.println(i + ") " + probabilidad1);
			} else {
				probabilidad2=((float)aciertos/(float)i);
			}
			resultado = probabilidad2-probabilidad1;
			if (resultado < 0) {
				resultado = resultado*(-1);
			} 
//			if (resultado < epsilon) {
//				i=M;
//				System.out.println("Corto");
//			}
			System.out.println(i + ") probabilidad2: " + probabilidad2 + " diferencia: " + resultado);
			probabilidad1=probabilidad2;
			i++;

		}
	}

	public static float calcularProbabilidadDeColision(int lugares [], int autos, int MM, String consigna) {
		int contar=0;
		float resultado = 0;
		for (int i = 0; i < MM; i++) {
			int entraron = estacionarEn(lugares, autos);
			if (entraron != -1) {
				contar++;
//				System.out.println("colisiones: " + contar); // es para ver.
			}
		}

		resultado = ((float)contar/(float)MM)*100;
		if (consigna == "C") {
			if (autos >= 10) {
				System.out.println("  "+ autos +" |  " + resultado + "%     |" + MM);				
			} else {
				System.out.println("  "+ autos +"  |  " + resultado + "%     |" + MM);						
			}			
		}
		return resultado;
	}

	public static int estacionarEn(int[] lugares, int autos) {
		for (int i = 0; i < lugares.length; i++) {
			lugares[i]=0;
		};
		int contador=0;
		int i = 0;
		while (i < autos ) {
			int nroPatente = (int) (Math.random() * patenteMax + 1);
			int EstacionaEnLugar = nroPatente%lugaresMax;
			if (lugares[EstacionaEnLugar] == 0) {
				lugares[EstacionaEnLugar]=nroPatente;
//				System.out.println(EstacionaEnLugar + ") " + "auto: " + (i+1) + " patente: " + lugares[EstacionaEnLugar]); //es para ver
				contador++;
				i++;
			} else {
				i=lugaresMax;
			}
		}
		if (contador == autos) {
			return -1;
		} else {
			return contador;				
		}
	}

}
