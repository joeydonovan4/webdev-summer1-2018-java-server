package edu.northeastern.cs4550.services.widget;

import java.util.List;

import edu.northeastern.cs4550.models.widget.Widget;

public interface IWidgetService {

    Widget createWidget(int lessonId, int topicId, Widget widget);

    Widget deleteWidget(int id);

    List<Widget> findAllWidgets();

    Widget findWidgetById(int id);

    List<Widget> findAllWidgetsForLessonTopic(int lessonId, int topicId);

    Widget updateWidget(Widget widget);

    List<Widget> saveWidgets(int topicId, List<Widget> widgets);
}
