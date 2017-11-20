package oops;

public class DBColumnSingleParam extends DBColumn
{
    protected DBColumnSingleParam(final String name, final String colType, final String isNullable, final int colSize, final String isAutoIncrement)
    {
        super(name, colType, isNullable, colSize, isAutoIncrement);   
    }

    @Override
    public String toSQL()
    {
        final StringBuffer sb = new StringBuffer();
        sb.append(String.format("\n%s %s(%d) %s%s,", this.getName(), this.getColType(), this.getColSize(), this.getIsNullable(), this.getIsAutoIncrement()));

        return sb.toString();
    }
}
