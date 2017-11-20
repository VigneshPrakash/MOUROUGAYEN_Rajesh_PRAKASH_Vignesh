package oops;

public class DBColumnDoubleParam extends DBColumn
{
    protected int scale;
    protected int precision;
            
    protected DBColumnDoubleParam(final String name, final String colType, final String isNullable, final int colSize, final int scale, final String isAutoIncrement)
    {
        super(name, colType, isNullable, colSize, isAutoIncrement);  
        this.scale = scale;
        this.precision = colSize;
    }

    @Override
    public String toSQL()
    {
        final StringBuffer sb = new StringBuffer();
        sb.append(String.format("\n%s %s(%d, %d) %s%s,", this.getName(), this.getColType(), this.precision, this.scale, this.getIsNullable(), this.getIsAutoIncrement()));

        return sb.toString();
    }

    public int getScale() {
        return scale;
    }

    public int getPrecision() {
        return precision;
    }
    
    
}
