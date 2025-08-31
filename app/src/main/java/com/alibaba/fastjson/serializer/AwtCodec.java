package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.constant.AbsoluteConst;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class AwtCodec implements ObjectSerializer, ObjectDeserializer {
    public static final AwtCodec instance = new AwtCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public static boolean support(Class<?> cls) {
        return cls == Point.class || cls == Rectangle.class || cls == Font.class || cls == Color.class;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Point.class, Operators.BLOCK_START), Constants.Name.X, point.x);
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, Constants.Name.Y, point.y);
        } else if (obj instanceof Font) {
            Font font = (Font) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Font.class, Operators.BLOCK_START), "name", font.getName());
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, "style", font.getStyle());
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, AbsoluteConst.JSON_KEY_SIZE, font.getSize());
        } else if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Rectangle.class, Operators.BLOCK_START), Constants.Name.X, rectangle.x);
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, Constants.Name.Y, rectangle.y);
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, "width", rectangle.width);
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, "height", rectangle.height);
        } else if (obj instanceof Color) {
            Color color = (Color) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Color.class, Operators.BLOCK_START), "r", color.getRed());
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, "g", color.getGreen());
            serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, "b", color.getBlue());
            if (color.getAlpha() > 0) {
                serializeWriter.writeFieldValue(Operators.ARRAY_SEPRATOR, "alpha", color.getAlpha());
            }
        } else {
            throw new JSONException("not support awt class : " + obj.getClass().getName());
        }
        serializeWriter.write(125);
    }

    protected char writeClassName(SerializeWriter serializeWriter, Class<?> cls, char c) {
        if (!serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
            return c;
        }
        serializeWriter.write(123);
        serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
        serializeWriter.writeString(cls.getName());
        return Operators.ARRAY_SEPRATOR;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        if (jSONLexer.token() != 12 && jSONLexer.token() != 16) {
            throw new JSONException("syntax error");
        }
        jSONLexer.nextToken();
        if (type == Point.class) {
            t = (T) parsePoint(defaultJSONParser, obj);
        } else if (type == Rectangle.class) {
            t = (T) parseRectangle(defaultJSONParser);
        } else if (type == Color.class) {
            t = (T) parseColor(defaultJSONParser);
        } else if (type == Font.class) {
            t = (T) parseFont(defaultJSONParser);
        } else {
            throw new JSONException("not support awt class : " + type);
        }
        ParseContext context = defaultJSONParser.getContext();
        defaultJSONParser.setContext(t, obj);
        defaultJSONParser.setContext(context);
        return t;
    }

    protected Font parseFont(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int iIntValue = 0;
        String strStringVal = null;
        int iIntValue2 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String strStringVal2 = jSONLexer.stringVal();
                jSONLexer.nextTokenWithColon(2);
                if (strStringVal2.equalsIgnoreCase("name")) {
                    if (jSONLexer.token() == 4) {
                        strStringVal = jSONLexer.stringVal();
                        jSONLexer.nextToken();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (strStringVal2.equalsIgnoreCase("style")) {
                    if (jSONLexer.token() == 2) {
                        iIntValue = jSONLexer.intValue();
                        jSONLexer.nextToken();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (strStringVal2.equalsIgnoreCase(AbsoluteConst.JSON_KEY_SIZE)) {
                    if (jSONLexer.token() == 2) {
                        iIntValue2 = jSONLexer.intValue();
                        jSONLexer.nextToken();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else {
                    throw new JSONException("syntax error, " + strStringVal2);
                }
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
            } else {
                throw new JSONException("syntax error");
            }
        }
        jSONLexer.nextToken();
        return new Font(strStringVal, iIntValue, iIntValue2);
    }

    protected Color parseColor(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String strStringVal = jSONLexer.stringVal();
                jSONLexer.nextTokenWithColon(2);
                if (jSONLexer.token() == 2) {
                    int iIntValue = jSONLexer.intValue();
                    jSONLexer.nextToken();
                    if (strStringVal.equalsIgnoreCase("r")) {
                        i = iIntValue;
                    } else if (strStringVal.equalsIgnoreCase("g")) {
                        i2 = iIntValue;
                    } else if (strStringVal.equalsIgnoreCase("b")) {
                        i3 = iIntValue;
                    } else {
                        if (!strStringVal.equalsIgnoreCase("alpha")) {
                            throw new JSONException("syntax error, " + strStringVal);
                        }
                        i4 = iIntValue;
                    }
                    if (jSONLexer.token() == 16) {
                        jSONLexer.nextToken(4);
                    }
                } else {
                    throw new JSONException("syntax error");
                }
            } else {
                throw new JSONException("syntax error");
            }
        }
        jSONLexer.nextToken();
        return new Color(i, i2, i3, i4);
    }

    protected Rectangle parseRectangle(DefaultJSONParser defaultJSONParser) {
        int iFloatValue;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String strStringVal = jSONLexer.stringVal();
                jSONLexer.nextTokenWithColon(2);
                int i5 = jSONLexer.token();
                if (i5 == 2) {
                    iFloatValue = jSONLexer.intValue();
                    jSONLexer.nextToken();
                } else if (i5 == 3) {
                    iFloatValue = (int) jSONLexer.floatValue();
                    jSONLexer.nextToken();
                } else {
                    throw new JSONException("syntax error");
                }
                if (strStringVal.equalsIgnoreCase(Constants.Name.X)) {
                    i = iFloatValue;
                } else if (strStringVal.equalsIgnoreCase(Constants.Name.Y)) {
                    i2 = iFloatValue;
                } else if (strStringVal.equalsIgnoreCase("width")) {
                    i3 = iFloatValue;
                } else {
                    if (!strStringVal.equalsIgnoreCase("height")) {
                        throw new JSONException("syntax error, " + strStringVal);
                    }
                    i4 = iFloatValue;
                }
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
            } else {
                throw new JSONException("syntax error");
            }
        }
        jSONLexer.nextToken();
        return new Rectangle(i, i2, i3, i4);
    }

    protected Point parsePoint(DefaultJSONParser defaultJSONParser, Object obj) {
        int iFloatValue;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = 0;
        int i2 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String strStringVal = jSONLexer.stringVal();
                if (JSON.DEFAULT_TYPE_KEY.equals(strStringVal)) {
                    defaultJSONParser.acceptType("java.awt.Point");
                } else {
                    if ("$ref".equals(strStringVal)) {
                        return (Point) parseRef(defaultJSONParser, obj);
                    }
                    jSONLexer.nextTokenWithColon(2);
                    int i3 = jSONLexer.token();
                    if (i3 == 2) {
                        iFloatValue = jSONLexer.intValue();
                        jSONLexer.nextToken();
                    } else if (i3 == 3) {
                        iFloatValue = (int) jSONLexer.floatValue();
                        jSONLexer.nextToken();
                    } else {
                        throw new JSONException("syntax error : " + jSONLexer.tokenName());
                    }
                    if (strStringVal.equalsIgnoreCase(Constants.Name.X)) {
                        i = iFloatValue;
                    } else {
                        if (!strStringVal.equalsIgnoreCase(Constants.Name.Y)) {
                            throw new JSONException("syntax error, " + strStringVal);
                        }
                        i2 = iFloatValue;
                    }
                    if (jSONLexer.token() == 16) {
                        jSONLexer.nextToken(4);
                    }
                }
            } else {
                throw new JSONException("syntax error");
            }
        }
        jSONLexer.nextToken();
        return new Point(i, i2);
    }

    private Object parseRef(DefaultJSONParser defaultJSONParser, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        lexer.nextTokenWithColon(4);
        String strStringVal = lexer.stringVal();
        defaultJSONParser.setContext(defaultJSONParser.getContext(), obj);
        defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(defaultJSONParser.getContext(), strStringVal));
        defaultJSONParser.popContext();
        defaultJSONParser.setResolveStatus(1);
        lexer.nextToken(13);
        defaultJSONParser.accept(13);
        return null;
    }
}
