package bootdemo.common.reflector;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
 * リフレクションでBeanの情報を読み書きするためのクラス
 */
public class BeanReflector {

    /**
     * 全プロパティ設定
     * @param  bean   - プロパティ設定対象のBean
     * @param  values - Beanのプロパティに設定する値を保持したMap
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws BeanReflectionException - リフレクション例外が発生した場合にスローされる
     */
    public static void setAllProperties(Object bean, Map values) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException  {
        // クラスに定義されている全てのpublicメソッドを取得
        Method[] methods = bean.getClass().getMethods();
        String propertyName = null;
        // メソッドの数だけループ
        for (int i = 0;i < methods.length;i++) {
            // "set"で始まる戻り値「void」のメソッドをBeanのSetterメソッドとみなす
            if ((methods[i].getName().startsWith("set"))
            &&  (Void.TYPE.equals(methods[i].getReturnType().toString()))) {
                // BeanのSetterメソッドは「setXxxxx」のため、プロパティ名の先頭を大文字にする
                propertyName = methods[i].getName().substring(3, 4).toLowerCase() + methods[i].getName().substring(4);
                // Setterメソッドを実行
                invokeSetterMethod(bean, methods[i], values.get(propertyName));
            }
        }
    }

    /**
     * 全プロパティ取得（取得結果は標準出力に出力）
     * @param  bean - プロパティ取得対象のBean
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws BeanReflectionException - リフレクション例外が発生した場合にスローされる
     */
    public static void getAllProperties(Object bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
    	 // クラスに定義されている全てのpublicメソッドを取得
        Method[] methods = bean.getClass().getMethods();
        // メソッドの数だけループ
        System.out.println("------------------------------------------------------------");
        for (int i = 0;i < methods.length;i++) {
            // "get"で始まるメソッドをBeanのGetterメソッドとみなす
            if (methods[i].getName().startsWith("get")) {
                // BeanのGetterメソッドは「getXxxxx」のため、getの次の文字を小文字とし、プロパティ名に出力
                System.out.print(methods[i].getName().substring(3, 4).toLowerCase());
                System.out.print(methods[i].getName().substring(4));
                System.out.print("=");
                // Getterメソッド実行
                System.out.println(methods[i].invoke(bean));
            }
        }
        System.out.println("------------------------------------------------------------");
    }

    /**
     * プロパティ設定
     * @param  bean  - プロパティ設定対象のBean
     * @param  name  - Beanのプロパティ名
     * @param  value - Beanのプロパティに設定する値
     * @throws IntrospectionException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws BeanReflectionException - リフレクション例外が発生した場合にスローされる
     */
    public static void setProperty(Object bean, String name, Object value) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException  {
    	// Beanの中の1個のSetterメソッドを探す場合、
        // Class.getMethodメソッドよりもPropertyDescriptorを使った方が簡単
        PropertyDescriptor pd = new PropertyDescriptor(name, bean.getClass());
        // 該当するSetterメソッドを取得
        Method method = pd.getWriteMethod();
        // Setterメソッドを実行
        invokeSetterMethod(bean, method, value);
    }

    /**
     * プロパティ取得
     * @param  bean - プロパティ取得対象のBean
     * @param  name - Beanのプロパティ名
     * @return 取得したプロパティの値
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws BeanReflectionException - リフレクション例外が発生した場合にスローされる
     */
    public static Object getProperty(Object bean, String name) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	 // Beanの中の1個のSetterメソッドを探す場合、
        // Class.getMethodメソッドよりもPropertyDescriptorを使った方が簡単
        PropertyDescriptor pd = new PropertyDescriptor(name, bean.getClass());
        // 該当するGetterメソッドを取得
        Method method = pd.getReadMethod();
        // Getterメソッドを実行し、その結果を返す
        return method.invoke(bean);
    }

    /**
     * インスタンス生成
     * @param  clazz - インスタンスを生成するクラスの型
     * @return 生成されたインスタンス
     * @throws BeanReflectionException - リフレクション例外が発生した場合にスローされる
     */
    public static <T> T createInstance(Class<T> clazz) {
    	 // 指定されたクラスのインスタンスを生成
        // return clazz.newInstance();
     	return  BeanUtils.instantiate(clazz);
    }

    /**
     * setterメソッド実行
     * @param  bean   - プロパティ設定対象のBean
     * @param  method - 実行対象のsetterメソッド
     * @param  value  - Beanのプロパティに設定する値
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws BeanReflectionException - リフレクション例外が発生した場合にスローされる
     */
    private static void invokeSetterMethod(Object bean, Method method, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        // 値が未設定の場合は何もせず処理終了
        if ((value == null) || ("".equals(value))) {
            return;
        }

        // Setterメソッドの第1引数の型を取得
        Class param = method.getParameterTypes()[0];
        // 第1引数のクラス名を取得
        String className = param.getSimpleName();

        // 第1引数の型を判定
        // 以下はプリミティブ型のため、クラス型に変換してSetterメソッドに渡す必要がある
        //   boolean、byte、char、double、float、int、long、short
        if (className.equals("boolean")) {
            method.invoke(bean, new Boolean(String.valueOf(value)));
        }
        else if (className.equals("byte")) {
            method.invoke(bean, new Byte(String.valueOf(value)));
        }
        else if (className.equals("char")) {
            method.invoke(bean, new Character((char)Integer.parseInt(String.valueOf(value))));
        }
        else if (className.equals("double")) {
            method.invoke(bean, new Double(String.valueOf(value)));
        }
        else if (className.equals("float")) {
            method.invoke(bean, new Float(String.valueOf(value)));
        }
        else if (className.equals("int")) {
            method.invoke(bean, new Integer(String.valueOf(value)));
        }
        else if (className.equals("long")) {
            method.invoke(bean, new Long(String.valueOf(value)));
        }
        else if (className.equals("short")) {
            method.invoke(bean, new Short(String.valueOf(value)));
        }
        // インタフェース（List、Mapなど）はスーパークラスを持たないため、そのままSetterメソッドに渡す
        // （なお、プリミティブ型もスーパークラスを持たない）
        else if (param.getSuperclass() == null) {
            method.invoke(bean, value);
        }
        // Numberを継承するクラス（Integer、BigDecimalなど）はString引数を持つコンストラクタで
        // 生成したインスタンスをSetterメソッドに渡す
        else if ("Number".equals(param.getSuperclass().getSimpleName())) {
            method.invoke(bean, param.getConstructor(String.class).newInstance(value.toString()));
        }
        // 上記以外については、そのままSetterメソッドに渡す
        // なお、他に上記のような判断が必要な型があれば、この上にif文を追加してゆく
        else {
            method.invoke(bean, value);

        }
    }
}
