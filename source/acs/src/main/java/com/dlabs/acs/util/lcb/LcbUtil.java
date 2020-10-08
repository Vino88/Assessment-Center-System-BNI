package com.dlabs.acs.util.lcb;

import com.dlabs.acs.entity.assessement.enumeration.LcbAnswer;
import com.dlabs.acs.entity.lcb.Lcb;

public class LcbUtil {
	public static Integer getWeightByAnswer(Lcb lcb, LcbAnswer answer)
	{
		if(LcbAnswer.A.equals(answer))
		{
			return lcb.getWeightA();
		} else if(LcbAnswer.B.equals(answer))
		{
			return lcb.getWeightB();
		}else if(LcbAnswer.C.equals(answer))
		{
			return lcb.getWeightC();
		}else if(LcbAnswer.D.equals(answer))
		{
			return lcb.getWeightD();
		}else if(LcbAnswer.E.equals(answer))
		{
			return lcb.getWeightE();
		}
		return 0;
	}
}
