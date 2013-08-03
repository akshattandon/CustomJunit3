package junit.framework;

import java.lang.reflect.Method;


class MethodPosition implements Comparable<MethodPosition> {
    private final Integer lineNumber;
    private final Integer indexInLine;
    private final String methodName;
    private final Method testmethod;

    public MethodPosition(int lineNumber, int indexInLine, String methodName, Method testmethod) {
        this.lineNumber = lineNumber;
        this.indexInLine = indexInLine;
        this.methodName = methodName;
        this.testmethod = testmethod;
    }
    public String getMethodName() {
		return methodName;
	}
	@Override
    public int compareTo(MethodPosition o) {
		  if (this.lineNumber.equals(o.lineNumber)) {
            return this.indexInLine.compareTo(o.indexInLine);
        } else {
            return this.lineNumber.compareTo(o.lineNumber);
        }
    }
	public Integer getLineNumber() {
		return lineNumber;
	}
	public Integer getIndexInLine() {
		return indexInLine;
	}
	
	public Method getTestMethod() {
		
		return this.testmethod;
	}
    
}	