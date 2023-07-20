package com.example.test.views;

import com.example.test.servises.CounterService;
import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class MainViewTest {

    private MainView mainView;
    private CounterService mockService;
    @Mock
    private UI ui;

    @BeforeEach
    void setUpUi() {
        openMocks(this);
        mainView = new MainView(mockService);
        UI.setCurrent(ui);
    }

    @BeforeEach
    void setUpService() {
        mockService = mock(CounterService.class);
        when(mockService.getCurrentValue()).thenReturn(0);
        mainView = new MainView(mockService);
    }

    @Test
    void initialValue_ShouldBeZero() {
        assertEquals("0", mainView.textField.getValue());
    }

    @Test
    void increaseButton_Click_ShouldIncrementValue() {
        UI.getCurrent().access(() -> {
            mainView.increaseButton.click();
            verify(mockService).incrementValue();
            assertEquals("1", mainView.textField.getValue());
        });
    }

    @Test
    void updateValue_ValidInput_ShouldUpdateValue() {
        mainView.textField.setValue("42");
        verify(mockService).setCurrentValue(42);
    }

    @Test
    void updateValue_InvalidInput_ShouldShowErrorMessage() {
        mainView.textField.setValue("abc");
    }
}
