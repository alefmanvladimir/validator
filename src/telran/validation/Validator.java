package telran.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

public class Validator {

    public static List<String> validate(Object obj){
        List<String> res = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).toList();
        for(int i=0; i<fields.size(); i++){
            Annotation[] annotations = fields.get(i).getAnnotations();
            for(int j=0; j<annotations.length; j++){
                List<String> messages = checkField(fields.get(i), annotations[j], obj);
                if(messages.size()>0){
                    res.addAll(messages);
                }
            }
        }
        return res;
    }

    private static List<String> checkField(Field field, Annotation annotation, Object obj) {
        List<String> res = new ArrayList<>();
        field.setAccessible(true);
        try {
            Method method = Validator.class.getDeclaredMethod("check" + annotation.annotationType().getSimpleName(), Object.class, Annotation.class);
            res.addAll((Collection<String>) method.invoke(Validator.class.getConstructor().newInstance(), field.get(obj), annotation));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return res;
    }

    private static List<String> checkNotEmpty(Object value, Annotation annotation) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String message = (String) value;
        Method annMethod = annotation.getClass().getDeclaredMethod("message");
        if(message.isEmpty()){
            return new ArrayList<>(Arrays.asList((String) annMethod.invoke(annotation)));
        } else {
            return new ArrayList<>();
        }
    }
    private static List<String> checkNotNull(Object value, Annotation annotation) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String message = (String) value;
        Method annMethod = annotation.getClass().getDeclaredMethod("message");
        if(message==null){
            return new ArrayList<>(Arrays.asList((String) annMethod.invoke(annotation)));
        } else {
            return new ArrayList<>();
        }
    }
    private static List<String> checkMax(Object value, Annotation annotation) throws Exception {
        Method annMethod = annotation.getClass().getDeclaredMethod("message");
        Method annValue = annotation.getClass().getDeclaredMethod("value");
        if((int) value > (int) annValue.invoke(annotation)){
            return new ArrayList<>(Arrays.asList((String) annMethod.invoke(annotation)));
        } else {
            return new ArrayList<>();
        }
    }
    private static List<String> checkMin(Object value, Annotation annotation) throws Exception {
        Method annMethod = annotation.getClass().getDeclaredMethod("message");
        Method annValue = annotation.getClass().getDeclaredMethod("value");
        if((int) value < (int) annValue.invoke(annotation)){
            return new ArrayList<>(Arrays.asList((String) annMethod.invoke(annotation)));
        } else {
            return new ArrayList<>();
        }
    }
    private static List<String> checkEmail(Object value, Annotation annotation) throws Exception {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Method annMethod = annotation.getClass().getDeclaredMethod("message");
        if(!pattern.matcher((String) value).matches()){
            return new ArrayList<>(Arrays.asList((String) annMethod.invoke(annotation)));
        } else {
            return new ArrayList<>();
        }
    }
    private static List<String> checkPast(Object value, Annotation annotation) throws Exception {
        Method annMethod = annotation.getClass().getDeclaredMethod("message");
        if(ChronoUnit.DAYS.between((LocalDate) value, LocalDate.now())>0){
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList((String) annMethod.invoke(annotation)));
        }
    }
    private static List<String> checkFuture(Object value, Annotation annotation) throws Exception {
        Method annMethod = annotation.getClass().getDeclaredMethod("message");
        if(ChronoUnit.DAYS.between((LocalDate) value, LocalDate.now())<0){
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList((String) annMethod.invoke(annotation)));
        }
    }
    private static List<String> checkValid(Object value, Annotation annotation) throws Exception{
        List<String> res = validate(value);
        return res;
    }
}
