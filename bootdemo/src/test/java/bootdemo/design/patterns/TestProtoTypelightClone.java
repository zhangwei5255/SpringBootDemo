package bootdemo.design.patterns;

import java.util.ArrayList;
import java.util.List;

import bootdemo.design.patterns.prototype.lightclone.Author;
import bootdemo.design.patterns.prototype.lightclone.Book;

/*浅克隆: 如果原型对象的成员变量是值类型，将复制一份给克隆对象；如果原型对象的成员变量是引用类型，
则将引用对象的地址复制一份给克隆对象，也就是说原型对象和克隆对象的成员变量指向相同的内存地址。简单来说，在浅克隆中，
当对象被复制时只复制它本身和其中包含的值类型的成员变量，而引用类型的成员对象并没有复制*/
public class TestProtoTypelightClone {
	public static void main(String[] args) {
		Author author = new Author();
		author.setName("tengj");
		Book book = new Book();
		book.setBookName("Java设计模式");
		book.setPrice(99);
		book.setAuthor(author);

		List<Author> lst = new ArrayList<Author>();
		author = new Author();
		author.setName("tengj1");
		lst.add(author);
		book.setLstAuthor(lst);

		Book book2 = book.clone();
		System.out.println(book == book2); // false
		System.out.println(book.getBookName() == book2.getBookName()); // true
		System.out.println(book.getAuthor() == book2.getAuthor()); // true
		System.out.println(book.getLstAuthor() == book2.getLstAuthor()); // true
	}
}
