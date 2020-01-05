package name.wildswift.testapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TestView extends FrameLayout {
    public final String tag;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        View.inflate(getContext(), R.layout.activity_main, this);
        tag = this.<TextView>findViewById(R.id.test).getText().toString();
    }
}
