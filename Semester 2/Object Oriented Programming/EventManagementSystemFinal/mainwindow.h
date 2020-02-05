#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

#include <Controller/Controller.h>
#include <Controller/userController.h>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();



private slots:
    void on_addToJoinedEvents_clicked();

//    void on_addToALL_clicked();

    void on_update_clicked();

    void on_remove_clicked();

    void on_GoToLink_clicked();

    void on_add_clicked();

    void on_undo_clicked();

    void on_redo_clicked();

    void on_table_clicked();

private:
    Ui::MainWindow *ui;
    Controller admin{1};
    userController user{admin};
};

#endif // MAINWINDOW_H
