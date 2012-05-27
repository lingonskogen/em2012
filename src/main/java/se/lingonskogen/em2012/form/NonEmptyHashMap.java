package se.lingonskogen.em2012.form;

import java.util.HashMap;

public abstract class NonEmptyHashMap<T1, T2> extends HashMap<T1, T2>
{
    private static final long serialVersionUID = 1L;

    @Override
    public T2 get(Object key)
    {
        if (!super.containsKey(key))
        {
            super.put((T1)key, build((T1)key));
        }
        return super.get(key);
    }


    public abstract T2 build(T1 key);
}
