package converterservice;

public class ConverterImpl implements Converter{

	@Override
	public int stringToInt(String str) {
		// TODO Auto-generated method stub
		return Integer.parseInt(str);
	}

	@Override
	public String intToString(int number) {
		// TODO Auto-generated method stub
		return Integer.toString(number);
	}

}
