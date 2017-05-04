package com.ipn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import static java.lang.Math.abs;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		SpecialView miVista = new SpecialView(this);
		setContentView(miVista);
	}


	
	class SpecialView extends View {
		float x = 50;
		float y = 50;
		String accion = "Accion";
		String texto = "Evento";
		Path path = new Path();
		float prex, prey;

		public SpecialView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			//canvas.drawColor(Color.rgb(255, 255, 150));
			canvas.drawColor(Color.WHITE); // color de fondo
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(6);
			paint.setColor(Color.RED);

			try {
				if (accion == "down") {
					path.moveTo(x, y);
				}

				if (accion == "move") {
					float r = abs((prex - x) / 2);
					path.addCircle(prex, prey, r, Path.Direction.CW);
				}
				if (accion == "up") {
					canvas.drawPath(path, paint);
				}
				paint.setColor(Color.BLACK);
				paint.setTextSize(20);
				paint.setStrokeWidth(2);
				canvas.drawText(texto, 100, 130, paint);
				canvas.drawText("x = " + x +
						"  y = " + y, 100, 50, paint);
				//canvas.drawc
			} catch (Exception e){
				System.out.println(e);
			}
		}
		@Override
		public boolean onTouchEvent(MotionEvent evento) {

			x = evento.getX();
			y = evento.getY();

			if (evento.getAction() == MotionEvent.ACTION_DOWN) {
				accion = "down";
				texto = "Action Down";
				prex = x;
				prey = y;
			}
			
			if (evento.getAction() == MotionEvent.ACTION_UP) { 
				texto = "Action Up";
				accion = "up";
			 }

			if (evento.getAction() == MotionEvent.ACTION_MOVE) {
				accion = "move";
				texto = "Action Move"; 
			}
			invalidate();
			return true;
		}
	}
}
