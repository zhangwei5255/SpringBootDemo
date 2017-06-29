package bootdemo.design.patterns;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bootdemo.design.patterns.prototype.deepclone.Author;
import bootdemo.design.patterns.prototype.deepclone.Book;



/*深复制不仅在堆内存上开辟了空间以存储复制出的对象，
 * 甚至连对象中的引用类型的属性所指向的对象也得以复制，重新开辟了堆空间存储。*/
public class TestProtoTypeDeepClone {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
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

		Book book2 = book.deepClone();

		System.out.println(book == book2); // false
		System.out.println(book.getBookName() == book2.getBookName()); // false
		System.out.println(book.getAuthor() == book2.getAuthor()); // false
		System.out.println(book.getLstAuthor() == book2.getLstAuthor()); // false
	}
}
