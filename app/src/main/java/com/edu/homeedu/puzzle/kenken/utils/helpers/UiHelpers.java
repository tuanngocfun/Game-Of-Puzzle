package com.edu.homeedu.puzzle.kenken.utils.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Utility class providing various UI helper methods.
 */
public final class UiHelpers {
    private UiHelpers() {}

    /**
     * Generates a new ID for the given view.
     *
     * @param view The view for which to generate a new ID.
     */
    public static void generateIdForView(View view) {
        int newId = View.generateViewId();
        view.setId(newId);
    }

    /**
     * Applies a consumer to a ViewGroup and all its children recursively.
     *
     * @param viewGroup The ViewGroup to consume.
     * @param consumer The consumer to apply.
     */
    public static void consumeViewGroupAndChildren(ViewGroup viewGroup, Consumer<View> consumer) {
        consumer.accept(viewGroup);

        Queue<ViewGroup> viewGroups = new ArrayDeque<>();
        viewGroups.add(viewGroup);
        while (!viewGroups.isEmpty()) {
            viewGroup = viewGroups.remove();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                consumer.accept(child);
                if (child instanceof ViewGroup) {
                    viewGroups.add((ViewGroup) child);
                }
            }
        }
    }

    /**
     * Retrieves a drawable resource.
     *
     * @param context The context to use for resource access.
     * @param drawableId The ID of the drawable resource.
     * @return The drawable resource.
     */
    public static Drawable getDrawable(Context context, int drawableId) {
        return ResourcesCompat.getDrawable(
                context.getResources(),
                drawableId,
                context.getTheme()
        );
    }

    /**
     * Retrieves a color resource.
     *
     * @param context The context to use for resource access.
     * @param colorId The ID of the color resource.
     * @return The color resource.
     */
    public static int getColor(Context context, int colorId) {
        return ResourcesCompat.getColor(
                context.getResources(),
                colorId,
                context.getTheme()
        );
    }

    /**
     * Calculates the inset stroke ignored offset.
     *
     * @param strokeWidth The width of the stroke.
     * @return The offset value.
     */
    public static int getInsetStrokeIgnoredOffset(int strokeWidth) {
        return -strokeWidth;
    }

    /**
     * Sets the background of a view.
     *
     * @param view The view for which to set the background.
     * @param context The context to use for resource access.
     * @param backgroundId The ID of the background resource.
     */
    public static void setViewBackground(View view, Context context, int backgroundId) {
        Drawable background = getDrawable(context, backgroundId);
        view.setBackground(background);
    }

    /**
     * Creates an information dialog.
     *
     * @param context The context to use for dialog creation.
     * @param title The title of the dialog.
     * @param message The message to display.
     * @param onOkClickedListener The listener for the OK button.
     * @return The created dialog.
     */
    public static AlertDialog createInfoDialog(Context context, String title, CharSequence message,
                                      DialogInterface.OnClickListener onOkClickedListener) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, onOkClickedListener);
        return dialogBuilder.create();
    }

    /**
     * Converts HTML text to a Spanned object.
     *
     * @param html The HTML text to convert.
     * @return The Spanned object.
     */
    public static Spanned fromHtml(String html) {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
    }

    /**
     * Retrieves a serializable object from a bundle.
     *
     * @param <T> The type of the serializable object.
     * @param bundle The bundle from which to retrieve the object.
     * @param key The key of the object in the bundle.
     * @param serializableClass The class of the serializable object.
     * @return The serializable object.
     */
    public static <T extends Serializable> T getSerializable(
            Bundle bundle,
            String key,
            Class<T> serializableClass
    ) {
        return bundle.getSerializable(key, serializableClass);
    }
}
