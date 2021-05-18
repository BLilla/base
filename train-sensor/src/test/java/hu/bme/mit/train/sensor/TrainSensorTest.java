package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
	
	TrainController controller;
	TrainUserImpl user;
	TrainSensorImpl sensor;

    @Before
    public void before() {
    	controller = mock(TrainController.class);
    	user = mock(TrainUserImpl.class);
    	sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void overAbsoluteMargin() {
        when(controller.getReferenceSpeed()).thenReturn(600);
        sensor.overrideSpeedLimit(600);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void underAbsoluteMargin() {
        when(controller.getReferenceSpeed()).thenReturn(-50);
        sensor.overrideSpeedLimit(-50);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void RelativeMargin() {
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void SetCorrectValueSpeedLimit() {
        when(controller.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(80);
        verify(user, times(0)).setAlarmState(false);
    }

}
