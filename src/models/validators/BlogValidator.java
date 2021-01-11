package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Blog;

public class BlogValidator {
    public static List<String> validate(Blog b) {
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(b.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = _validateContent(b.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
            }

        return "";
    }
}