package TCP_UDP_HTTP;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Main {

}
// I'm forget what do this class
class Cat implements Externalizable
{
    public String name;
    public int age;
    public int weight;

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
        out.writeInt(weight);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
        weight = in.readInt();
    }
}