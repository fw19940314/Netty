package com.sentry;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import io.sentry.context.Context;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/31
 * @Description:
 */
public class MySentry {
    private static SentryClient sentry;

    public static void main(String[] args) {
        /*
         It is recommended that you use the DSN detection system, which
         will check the environment variable "SENTRY_DSN", the Java
         System Property "sentry.dsn", or the "sentry.properties" file
         in your classpath. This makes it easier to provide and adjust
         your DSN without needing to change your code. See the configuration
         page for more information.
         */
        Sentry.init();

        // You can also manually provide the DSN to the ``init`` method.
        String dsn = "http://3bca34d4c4c14ca9b18820feeaf7446a:a89ee9b908094651b5537910394ea5fb@47.98.120.163:9000/6";
        Sentry.init(dsn);

        /*
         It is possible to go around the static ``Sentry`` API, which means
         you are responsible for making the SentryClient instance available
         to your code.
         */
        sentry = SentryClientFactory.sentryClient();


        MySentry myClass = new MySentry();
        myClass.logWithStaticAPI();
        myClass.logWithInstanceAPI();
    }
    /**
     * An example method that throws an exception.
     */
    void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }

    /**
     * Examples using the (recommended) static API.
     */
    void logWithStaticAPI() {
        // Note that all fields set on the context are optional. Context data is copied onto
        // all future events in the current context (until the context is cleared).

        // Record a breadcrumb in the current context. By default the last 100 breadcrumbs are kept.
        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder().setMessage("User made an action").build()
        );

        // Set the user in the current context.
        Sentry.getContext().setUser(
                new UserBuilder().setEmail("hello@sentry.io").build()
        );

        // Add extra data to future events in this context.
        Sentry.getContext().addExtra("extra", "thing");

        // Add an additional tag to future events in this context.
        Sentry.getContext().addTag("tagName", "tagValue");

        /*
         This sends a simple event to Sentry using the statically stored instance
         that was created in the ``main`` method.
         */
        Sentry.capture("This is a test");

        try {
            unsafeMethod();
        } catch (Exception e) {
            // This sends an exception event to Sentry using the statically stored instance
            // that was created in the ``main`` method.
            Sentry.capture(e);
        }
    }

    /**
     * Examples that use the SentryClient instance directly.
     */
    void logWithInstanceAPI() {
        // Retrieve the current context.
        Context context = sentry.getContext();

        // Record a breadcrumb in the current context. By default the last 100 breadcrumbs are kept.
        context.recordBreadcrumb(new BreadcrumbBuilder().setMessage("User made an action").build());

        // Set the user in the current context.
        context.setUser(new UserBuilder().setEmail("hello@sentry.io").build());

        // This sends a simple event to Sentry.
        sentry.sendMessage("This is a test");

        try {
            unsafeMethod();
        } catch (Exception e) {
            // This sends an exception event to Sentry.
            sentry.sendException(e);
        }
    }
}
