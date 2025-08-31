package com.taobao.weex.el.parse;

/* loaded from: classes.dex */
class Operator extends Token {
    public Token first;
    public Token second;
    public Token self;

    public Operator(String str, int i) {
        super(str, i);
    }

    @Override // com.taobao.weex.el.parse.Token
    public Object execute(Object obj) {
        String token;
        boolean z;
        token = getToken();
        token.hashCode();
        token.hashCode();
        switch (token) {
            case "!":
                return Boolean.valueOf(!Operators.tokenTrue(this.self, obj));
            case "%":
                return Operators.mod(this.first, this.second, obj);
            case "*":
                return Operators.mul(this.first, this.second, obj);
            case "+":
                return Operators.plus(this.first, this.second, obj);
            case "-":
                return Operators.sub(this.first, this.second, obj);
            case ".":
            case "[":
                return Operators.dot(this.first, this.second, obj);
            case "/":
                return Operators.div(this.first, this.second, obj);
            case "<":
                return Boolean.valueOf(Operators.tokenNumber(this.first, obj) < Operators.tokenNumber(this.second, obj));
            case ">":
                return Boolean.valueOf(Operators.tokenNumber(this.first, obj) > Operators.tokenNumber(this.second, obj));
            case "?":
                return Operators.condition(this.self, this.first, this.second, obj);
            case "!=":
            case "!==":
                return Boolean.valueOf(!Operators.isEquals(this.first, this.second, obj));
            case "&&":
                if (Operators.tokenTrue(this.first, obj) && Operators.tokenTrue(this.second, obj)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case "<=":
                return Boolean.valueOf(Operators.tokenNumber(this.first, obj) <= Operators.tokenNumber(this.second, obj));
            case "==":
            case "===":
                return Boolean.valueOf(Operators.isEquals(this.first, this.second, obj));
            case ">=":
                return Boolean.valueOf(Operators.tokenNumber(this.first, obj) >= Operators.tokenNumber(this.second, obj));
            case "||":
                return Boolean.valueOf(Operators.tokenTrue(this.first, obj) || Operators.tokenTrue(this.second, obj));
            default:
                throw new IllegalArgumentException(token + " operator is not supported");
        }
    }

    @Override // com.taobao.weex.el.parse.Token
    public String toString() {
        if (Operators.AND_NOT.equals(getToken())) {
            return "{!" + this.self + Operators.BLOCK_END_STR;
        }
        if (this.self == null) {
            return Operators.BLOCK_START_STR + this.first + getToken() + this.second + Operators.BLOCK_END_STR;
        }
        return Operators.BLOCK_START_STR + this.self + getToken() + this.first + ":" + this.second + Operators.BLOCK_END_STR;
    }
}
