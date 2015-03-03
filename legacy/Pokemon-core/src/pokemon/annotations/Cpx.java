package pokemon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//dfg
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
public @interface Cpx {
	public String cpx() default "n";  
}
