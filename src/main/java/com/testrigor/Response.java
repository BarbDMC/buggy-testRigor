package com.testrigor;

import java.util.List;
import java.util.Objects;

class Response {
		List<Person> data;

		public List<Person> getData() {
			return data;
		}

		public void setData(List<Person> data) {
			this.data = data;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Response response = (Response) o;
			return Objects.equals(data, response.data);
		}

		@Override
		public int hashCode() {
			return Objects.hash(data);
		}
	}