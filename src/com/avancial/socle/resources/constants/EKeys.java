package com.avancial.socle.resources.constants;

/**
 * 
 * @author caglar.erdogan
 *
 */
public enum EKeys {
	ALT(18),
	BACKSPACE(8),
	CAPS_LOCK(20),
	CTRL(17),
	DOWN_ARROW(40),
	END(35),
	ENTER(13),
	ESCAPE(27),
	F1(112),
	F10(121),
	F11(122),
	F12(123),
	F2(113),
	F3(114),
	F4(115),
	F5(116),
	F6(117),
	F7(118),
	F8(119),
	F9(120),
	HOME(36),
	INSERT(45),
	LEFT_ARROW(37),
	LEFT_WINDOW(91),
	NUM_LOCK(144),
	PAGE_DOWN(34),
	PAGE_UP(33),
	PAUSE_BREAK(19),
	PRINT_SCREEN(44),
	RIGHT_ARROW(39),
	RIGHT_WINDOW(92),
	SCROLL_LOCK(145),
	SELECT(93),
	SHIFT(16),
	SPACE(32),
	TAB(9),
	UP_ARROW(38);

	public static int[] getInvalidInputKeys() {
		return new int[] {ALT.code, CAPS_LOCK.code, CTRL.code, DOWN_ARROW.code, END.code, ESCAPE.code, F1.code, F10.code, F11.code, F12.code, F2.code, F3.code, F4.code, F5.code, F6.code, F7.code, F8.code, F9.code, HOME.code, INSERT.code, LEFT_ARROW.code, LEFT_WINDOW.code, NUM_LOCK.code, PAGE_DOWN.code, PAGE_UP.code, PAUSE_BREAK.code, PRINT_SCREEN.code, RIGHT_ARROW.code, RIGHT_WINDOW.code, SCROLL_LOCK.code, SELECT.code, SHIFT.code, TAB.code, UP_ARROW.code};
	}

	private int code;

	private EKeys(int code) {
		this.code = code;
	}
}