package hu.cookerybook.core.dbconn;

public class PreparedStatementParameter {

    private int parameterIndex;
    private String parameterType;
    private String stringData;
    private Integer integerData;
    private Float floatData;

    public PreparedStatementParameter(int parameterIndex, String parameterType, String stringData) {
        this.parameterIndex = parameterIndex;
        this.parameterType = parameterType;
        this.stringData = stringData;
    }

    public PreparedStatementParameter(int parameterIndex, String parameterType, Integer integerData) {
        this.parameterIndex = parameterIndex;
        this.parameterType = parameterType;
        this.integerData = integerData;
    }

    public PreparedStatementParameter(int parameterIndex, String parameterType, Float floatData) {
        this.parameterIndex = parameterIndex;
        this.parameterType = parameterType;
        this.floatData = floatData;
    }

    public int getParameterIndex() {
        return parameterIndex;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getStringData() {
        return stringData;
    }

    public Integer getIntegerData() {
        return integerData;
    }

    public Float getFloatData() {
        return floatData;
    }

    @Override
    public String toString() {
        return "PreparedStatementParameter{" +
                "parameterIndex=" + parameterIndex +
                ", parameterType='" + parameterType + '\'' +
                ", stringData='" + stringData + '\'' +
                ", integerData=" + integerData +
                ", floatData=" + floatData +
                '}';
    }
}
