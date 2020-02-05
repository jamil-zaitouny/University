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
    void on_add_clicked();
private:
    Ui::MainWindow *ui;
    Controller admin{};
    userController user{admin};
};

#endif // MAINWINDOW_H
