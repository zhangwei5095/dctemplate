package me.j360.bootnative.logback;

import ch.qos.logback.classic.pattern.*;
import ch.qos.logback.classic.pattern.color.HighlightingCompositeConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.pattern.color.*;
import ch.qos.logback.core.pattern.parser.Parser;
import me.j360.bootnative.logback.pattern.DiyExtendedThrowableProxyConverter;
import me.j360.bootnative.logback.pattern.DiyRootCauseFirstThrowableProxyConverter;
import me.j360.bootnative.logback.pattern.DiyThrowableProxyConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: me.j360.bootnative.logback
 * User: min_xu
 * Date: 16/7/15 下午8:06
 * 说明：
 */
public class DiyPatternLayout extends PatternLayoutBase<ILoggingEvent> {

    public static final Map<String, String> defaultConverterMap = new HashMap<String, String>();
    public static final String HEADER_PREFIX = "#logback.classic pattern: ";

    static {
        defaultConverterMap.putAll(Parser.DEFAULT_COMPOSITE_CONVERTER_MAP);

        defaultConverterMap.put("d", DateConverter.class.getName());
        defaultConverterMap.put("date", DateConverter.class.getName());

        defaultConverterMap.put("r", RelativeTimeConverter.class.getName());
        defaultConverterMap.put("relative", RelativeTimeConverter.class.getName());

        defaultConverterMap.put("level", LevelConverter.class.getName());
        defaultConverterMap.put("le", LevelConverter.class.getName());
        defaultConverterMap.put("p", LevelConverter.class.getName());

        defaultConverterMap.put("t", ThreadConverter.class.getName());
        defaultConverterMap.put("thread", ThreadConverter.class.getName());

        defaultConverterMap.put("lo", LoggerConverter.class.getName());
        defaultConverterMap.put("logger", LoggerConverter.class.getName());
        defaultConverterMap.put("c", LoggerConverter.class.getName());

        defaultConverterMap.put("m", MessageConverter.class.getName());
        defaultConverterMap.put("msg", MessageConverter.class.getName());
        defaultConverterMap.put("message", MessageConverter.class.getName());

        defaultConverterMap.put("C", ClassOfCallerConverter.class.getName());
        defaultConverterMap.put("class", ClassOfCallerConverter.class.getName());

        defaultConverterMap.put("M", MethodOfCallerConverter.class.getName());
        defaultConverterMap.put("method", MethodOfCallerConverter.class.getName());

        defaultConverterMap.put("L", LineOfCallerConverter.class.getName());
        defaultConverterMap.put("line", LineOfCallerConverter.class.getName());

        defaultConverterMap.put("F", FileOfCallerConverter.class.getName());
        defaultConverterMap.put("file", FileOfCallerConverter.class.getName());

        defaultConverterMap.put("X", MDCConverter.class.getName());
        defaultConverterMap.put("mdc", MDCConverter.class.getName());

        defaultConverterMap.put("ex", DiyThrowableProxyConverter.class.getName());
        defaultConverterMap.put("exception", DiyThrowableProxyConverter.class
                .getName());
        defaultConverterMap.put("rEx", DiyRootCauseFirstThrowableProxyConverter.class.getName());
        defaultConverterMap.put("rootException", DiyRootCauseFirstThrowableProxyConverter.class
                .getName());
        defaultConverterMap.put("throwable", DiyThrowableProxyConverter.class
                .getName());

        defaultConverterMap.put("xEx", DiyExtendedThrowableProxyConverter.class.getName());
        defaultConverterMap.put("xException", DiyExtendedThrowableProxyConverter.class
                .getName());
        defaultConverterMap.put("xThrowable", DiyExtendedThrowableProxyConverter.class
                .getName());

        defaultConverterMap.put("nopex", NopThrowableInformationConverter.class
                .getName());
        defaultConverterMap.put("nopexception",
                NopThrowableInformationConverter.class.getName());

        defaultConverterMap.put("cn", ContextNameConverter.class.getName());
        defaultConverterMap.put("contextName", ContextNameConverter.class.getName());

        defaultConverterMap.put("caller", CallerDataConverter.class.getName());

        defaultConverterMap.put("marker", MarkerConverter.class.getName());

        defaultConverterMap.put("property", PropertyConverter.class.getName());

        defaultConverterMap.put("n", LineSeparatorConverter.class.getName());

        defaultConverterMap.put("black", BlackCompositeConverter.class.getName());
        defaultConverterMap.put("red", RedCompositeConverter.class.getName());
        defaultConverterMap.put("green", GreenCompositeConverter.class.getName());
        defaultConverterMap.put("yellow", YellowCompositeConverter.class.getName());
        defaultConverterMap.put("blue", BlueCompositeConverter.class.getName());
        defaultConverterMap.put("magenta", MagentaCompositeConverter.class.getName());
        defaultConverterMap.put("cyan", CyanCompositeConverter.class.getName());
        defaultConverterMap.put("white", WhiteCompositeConverter.class.getName());
        defaultConverterMap.put("gray", GrayCompositeConverter.class.getName());
        defaultConverterMap.put("boldRed", BoldRedCompositeConverter.class.getName());
        defaultConverterMap.put("boldGreen", BoldGreenCompositeConverter.class.getName());
        defaultConverterMap.put("boldYellow", BoldYellowCompositeConverter.class.getName());
        defaultConverterMap.put("boldBlue", BoldBlueCompositeConverter.class.getName());
        defaultConverterMap.put("boldMagenta", BoldMagentaCompositeConverter.class.getName());
        defaultConverterMap.put("boldCyan", BoldCyanCompositeConverter.class.getName());
        defaultConverterMap.put("boldWhite", BoldWhiteCompositeConverter.class.getName());
        defaultConverterMap.put("highlight", HighlightingCompositeConverter.class.getName());



    }

    public DiyPatternLayout() {
        this.postCompileProcessor = new EnsureExceptionHandling();
    }

    public Map<String, String> getDefaultConverterMap() {
        return defaultConverterMap;
    }

    public String doLayout(ILoggingEvent event) {
        if (!isStarted()) {
            return CoreConstants.EMPTY_STRING;
        }
        return writeLoopOnConverters(event);
    }

    @Override
    protected String getPresentationHeaderPrefix() {
        return HEADER_PREFIX;
    }
}
